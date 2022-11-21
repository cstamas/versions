package org.codehaus.mojo.versions;

/*
 * Copyright MojoHaus and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.apache.maven.wagon.Wagon;
import org.codehaus.mojo.versions.reporting.ReportRendererFactory;
import org.codehaus.mojo.versions.reporting.util.AggregateReportUtils;
import org.codehaus.plexus.i18n.I18N;

/**
 * Generates an aggregated report of available updates for the plugins of a project.
 *
 * @since 2.14.0
 */
@Mojo( name = "plugin-updates-aggregate-report", requiresDependencyResolution = ResolutionScope.RUNTIME,
       threadSafe = true, aggregator = true )
public class PluginUpdatesAggregateReportMojo extends AbstractPluginUpdatesReportMojo
{

    @Inject
    protected PluginUpdatesAggregateReportMojo( I18N i18n, RepositorySystem repositorySystem,
                                                org.eclipse.aether.RepositorySystem aetherRepositorySystem,
                                                Map<String, Wagon> wagonMap,
                                                ReportRendererFactory rendererFactory )
    {
        super( i18n, repositorySystem, aetherRepositorySystem, wagonMap, rendererFactory );
    }

    @Override
    protected void populatePluginManagement( Set<Plugin> pluginManagementCollector )
    {
        for ( MavenProject project : AggregateReportUtils.getProjectsToProcess( getProject() ) )
        {
            if ( haveBuildPluginManagementPlugins( project ) )
            {
                pluginManagementCollector.addAll( project.getBuild().getPluginManagement().getPlugins() );
            }
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void populatePlugins( Set<Plugin> pluginsCollector )
    {
        for ( MavenProject project : AggregateReportUtils.getProjectsToProcess( getProject() ) )
        {
            if ( haveBuildPluginManagementPlugins( project ) )
            {
                pluginsCollector.addAll( project.getBuild().getPlugins() );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getOutputName()
    {
        return "plugin-updates-aggregate-report";
    }

}
