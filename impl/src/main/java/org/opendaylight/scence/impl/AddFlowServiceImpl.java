package org.opendaylight.scence.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.opendaylight.scence.tools.AppConfig;
import org.opendaylight.scence.tools.ParseXml;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AustinService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class AddFlowServiceImpl implements AustinService {
    private static final Logger LOG = LoggerFactory.getLogger(AddFlowServiceImpl.class);
    private static final String url = "http://localhost:8181/operations/sal-flow:add-flow";
    HttpClient httpclient = new DefaultHttpClient();

    @Override
    public Future<RpcResult<AddFlowOutput>> addFlow(AddFlowInput input) {
        String fileName = input.getFlowFile();
        LOG.info("flow-file=%  s",fileName);
        String body = ParseXml.buildRequestBody(fileName);
        LOG.info("flow-file content is : \r\n % s",body);
        List<NameValuePair> parms = new ArrayList<NameValuePair>();
        NameValuePair pair = new BasicNameValuePair("Content-Type", "application/xml");
        parms.add(pair);
        String resl = post(url,fileName,"utf-8","utf-8",parms);
       AddFlowOutputBuilder outputBuilder = new AddFlowOutputBuilder();
       
       outputBuilder.setResult(resl);
       LOG.info(resl);
       return RpcResultBuilder.success(outputBuilder.build()).buildFuture();
       
    }

    private String post(String url, String file,String reqEncoding, String respEncoding,
            List<NameValuePair> param) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope("127.0.0.1", 8181),
                new UsernamePasswordCredentials(AppConfig.getInstance().getUsername(), AppConfig.getInstance().getPassword()));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();

        String resStr = "";
        InputStreamEntity reqEntity = new InputStreamEntity(
                ParseXml.buildRequestEntity(file), -1, ContentType.APPLICATION_XML);
        reqEntity.setChunked(true);
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        List<NameValuePair> formparams = param;
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, reqEncoding);
            httppost.setEntity(uefEntity);
            HttpResponse response;
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                resStr = EntityUtils.toString(entity, respEncoding);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            // httpclient.getConnectionManager().shutdown();
        }
        return resStr;
    }
}
