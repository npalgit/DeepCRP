		public PaddedBatchingEntityLoader(
				OuterJoinLoadable persister,
				int maxBatchSize,
				LockMode lockMode,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers loadQueryInfluencers) {
			super( persister );
			this.batchSizes = ArrayHelper.getBatchSizes( maxBatchSize );
			this.loaders = new Loader[ batchSizes.length ];
			for ( int i = 0; i < batchSizes.length; i++ ) {
				this.loaders[i] = new EntityLoader( persister, batchSizes[i], lockMode, factory, loadQueryInfluencers);
			}
			validate( maxBatchSize );
		}
