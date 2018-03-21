
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;

// ZooKeeper API 删除节点，使用同步(sync)接口。
public class Monitor2 {

	public static void main(String[] args) throws Exception {
		ZkClient zkClient = new ZkClient("127.0.0.1:2181");
    	String path = "/monitor/abc";
    	zkClient.subscribeDataChanges(path, new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println(dataPath);
				
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println(dataPath+data);
				
			}
		});
    	zkClient.subscribeStateChanges(new IZkStateListener() {
			
			@Override
			public void handleStateChanged(KeeperState state) throws Exception {
				System.out.println(state);
			}
			
			@Override
			public void handleSessionEstablishmentError(Throwable error) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void handleNewSession() throws Exception {
				// TODO Auto-generated method stub
				
			}
		});
		zkClient.subscribeChildChanges(path, new IZkChildListener() {
			
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println("currentChilds");
				for (String string : currentChilds) {
					System.out.println(string);
				}
			}
		});
		Thread.sleep(Integer.MAX_VALUE);
	}
}