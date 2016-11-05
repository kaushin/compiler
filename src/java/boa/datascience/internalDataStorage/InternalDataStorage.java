package boa.datascience.internalDataStorage;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.aol.cyclops.data.async.Queue;
import com.google.protobuf.GeneratedMessage;

import boa.datascience.DataScienceComponent;
import boa.datascience.externalDataSources.DatagenProperties;
import boa.datascience.internalDataStorage.hadoopSequenceFile.SequenceFileStorage;

public class InternalDataStorage extends DataScienceComponent {
	protected static Logger LOG = Logger.getLogger(InternalDataStorage.class);
	private SequenceFileStorage storage;

	public InternalDataStorage(String parser) {
		this.storage = new SequenceFileStorage(
				DatagenProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DatagenProperties.HADOOP_SEQ_FILE_NAME, parser);
	}

	public void store(List<GeneratedMessage> dataInstance) {
		this.storage.store(dataInstance);
	}

	public void store(Queue<GeneratedMessage> queue) {
		if (queue == null) {
			throw new UnsupportedOperationException();
		}
		this.storage.store(queue.stream().map(x -> x).collect(Collectors.toList()));

	}

	@Override
	public List<GeneratedMessage> getData() {
		return this.storage.getData();
	}

//	@Override
//	public boolean getDataInQueue(Queue<GeneratedMessage> queue) {
//		if (queue == null || !queue.isOpen()) {
//			throw new IllegalStateException("Queue is either null or has been closed");
//		}
//		getData().stream().forEach(data -> queue.offer(data));
//		queue.close();
//		return true;
//	}

}