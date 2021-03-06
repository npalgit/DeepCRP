    public synchronized boolean execute(
            String sql, int autoGeneratedKeys) throws SQLException {

        if (autoGeneratedKeys != Statement.RETURN_GENERATED_KEYS
                && autoGeneratedKeys != Statement.NO_GENERATED_KEYS) {
            throw JDBCUtil.invalidArgument("autoGeneratedKeys");
        }
        fetchResult(sql, StatementTypes.RETURN_ANY, autoGeneratedKeys, null,
                    null);

        return resultIn.isData();
    }
