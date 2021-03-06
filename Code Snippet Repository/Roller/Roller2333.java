    public void loadComments() {

        List<WeblogEntryComment> comments = Collections.emptyList();
        boolean hasMore = false;
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger()
                    .getWeblogEntryManager();

            // lookup weblog entry if necessary
            if (!StringUtils.isEmpty(getBean().getEntryId())) {
                setQueryEntry(wmgr.getWeblogEntry(getBean().getEntryId()));
            }

            CommentSearchCriteria csc = new CommentSearchCriteria();
            csc.setWeblog(getActionWeblog());
            csc.setEntry(getQueryEntry());
            csc.setSearchText(getBean().getSearchString());
            csc.setStartDate(getBean().getStartDate());
            csc.setEndDate(getBean().getEndDate());
            csc.setStatus(getBean().getStatus());
            csc.setReverseChrono(true);
            csc.setOffset(getBean().getPage() * COUNT);
            csc.setMaxResults(COUNT + 1);

            List<WeblogEntryComment> rawComments = wmgr.getComments(csc);
            comments = new ArrayList<WeblogEntryComment>();
            comments.addAll(rawComments);
            if (comments.size() > 0) {
                if (comments.size() > COUNT) {
                    comments.remove(comments.size() - 1);
                    hasMore = true;
                }

                setFirstComment(comments.get(0));
                setLastComment(comments.get(comments
                        .size() - 1));
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up comments", ex);
            addError("Error looking up comments");
        }

        // build comments pager
        String baseUrl = buildBaseUrl();
        setPager(new CommentsPager(baseUrl, getBean().getPage(), comments,
                hasMore));
    }
