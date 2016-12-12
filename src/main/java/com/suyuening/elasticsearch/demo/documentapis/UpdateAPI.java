package com.suyuening.elasticsearch.demo.documentapis;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.ActionWriteResponse.ShardInfo;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

import com.suyuening.elasticsearch.utils.ESClient;

/**
 * <a href=
 * "https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-update.html"
 * target="_blank">查看ES Doc</a>
 * 
 * @author suyuening
 *
 */
public class UpdateAPI {
	public static void main(String[] args) {

		// 方法一
		UpdateResponse response = null;
		try (Client client = ESClient.client()) {
			response = client.prepareUpdate("twitter", "tweet", "2")
					.setDoc(jsonBuilder().startObject().field("message", "Hello suyuening!").endObject()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (response != null) {
			System.out.println(response.getIndex());
			System.out.println(response.getType());
			System.out.println(response.getId());
			System.out.println(response.getVersion());

			ShardInfo shardInfo = response.getShardInfo();
			System.out.println(shardInfo.getTotal());
			System.out.println(shardInfo.getFailed());
			System.out.println(shardInfo.getSuccessful());
		}

	}
	// 方法二
	// UpdateRequest updateRequest = new UpdateRequest();
	// updateRequest.index("twitter");
	// updateRequest.type("tweet");
	// updateRequest.id("2");
	//
	// try {
	// updateRequest.doc(jsonBuilder()
	// .startObject()
	// .field("message", "Hello suyuening")
	// .endObject());
	// ESClient.client().update(updateRequest).get();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// } catch (ExecutionException e) {
	// e.printStackTrace();
	// }

	// 方法三
	// ESClient.client().prepareUpdate("ttl", "doc", "1")
	// .setScript(new Script("ctx._source.gender = \"male\"" ,
	// ScriptService.ScriptType.INLINE, null, null))
	// .get();

}
