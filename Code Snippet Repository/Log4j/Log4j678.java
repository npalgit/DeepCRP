    private RandomAccessFileAppender(final String name, final Layout<? extends Serializable> layout,
            final Filter filter, final RandomAccessFileManager manager, final String filename,
            final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser) {

        super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
        if (advertiser != null) {
            final Map<String, String> configuration = new HashMap<>(
                    layout.getContentFormat());
            configuration.putAll(manager.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            advertisement = advertiser.advertise(configuration);
        }
        this.fileName = filename;
        this.advertiser = advertiser;
    }
