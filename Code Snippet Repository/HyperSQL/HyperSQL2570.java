    void populateBySerialIntKeysInt(DoubleIntIndex intLookup,
                                    org.hsqldb.lib.IntKeyHashMap hMap,
                                    int testSize) throws Exception {

        for (int i = 0; i < testSize; i++) {
            int intValue = randomgen.nextInt();

            intLookup.addUnique(i, intValue);
            hMap.put(i, new Integer(intValue));

            if (intLookup.size() != hMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }
    }
