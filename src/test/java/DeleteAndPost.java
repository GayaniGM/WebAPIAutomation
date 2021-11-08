import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAndPost extends BaseClass{
	
	CloseableHttpClient client;
	static CloseableHttpResponse response;
	
	@Test
	public void deleteIsSuccessful() throws IOException{
		
		HttpDelete request = new HttpDelete("https://api.github.com/repos/gmgunawardhana/deleteme");
		response = client.execute(request);
		
		int actualStatusCode = response.getStatusLine().getStatusCode();
		
		Assert.assertEquals(actualStatusCode, 204);
		
		
		
		
	}
}