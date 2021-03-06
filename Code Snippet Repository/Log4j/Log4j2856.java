    @Benchmark
    public int iterateArrayContextDataBiConsumer() {
        final int[] result = {0};

        populatedSortedStringArrayMap.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(final String s, final Object o) {
                result[0] += s.hashCode() + o.hashCode();
            }
        });
        return result[0];
    }
