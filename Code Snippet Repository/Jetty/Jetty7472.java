    public TestableJettyServer() throws IOException
    {
        _xmlConfigurations = new ArrayList<URL>();
        Properties properties = new Properties();

        /* Establish Popular Directories */
        String baseDirPath = System.getProperty("basedir");
        if (baseDirPath == null)
        {
            baseDirPath = System.getProperty("user.dir",".");
        }
        baseDir = new File(baseDirPath);
        properties.setProperty("test.basedir",baseDir.getAbsolutePath());

        testResourcesDir = new File(baseDirPath,"src/test/resources".replace('/',File.separatorChar));
        properties.setProperty("test.resourcesdir",testResourcesDir.getAbsolutePath());

        File testDocRoot = new File(testResourcesDir,"docroots");
        properties.setProperty("test.docroot.base",testDocRoot.getAbsolutePath());

        File targetDir = new File(baseDir,"target");
        properties.setProperty("test.targetdir",targetDir.getAbsolutePath());

        File webappsDir = new File(targetDir,"webapps");
        properties.setProperty("test.webapps",webappsDir.getAbsolutePath());

        // Write out configuration for use by ConfigurationManager.
        File testConfig = new File(targetDir,"testable-jetty-server-config.properties");
        FileOutputStream out = new FileOutputStream(testConfig);
        properties.store(out,"Generated by " + TestableJettyServer.class.getName());
        
        for (Object key:properties.keySet())
            _properties.put(String.valueOf(key),String.valueOf(properties.get(key)));
    }
