    @Override
    public MavenExecutionRequest setPluginArtifactRepositories( List<ArtifactRepository> pluginArtifactRepositories )
    {
        if ( pluginArtifactRepositories != null )
        {
            this.pluginArtifactRepositories = new ArrayList<>( pluginArtifactRepositories );
        }
        else
        {
            this.pluginArtifactRepositories = null;
        }

        return this;
    }
