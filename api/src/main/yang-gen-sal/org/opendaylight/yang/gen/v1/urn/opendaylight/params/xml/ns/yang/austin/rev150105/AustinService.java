package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module &lt;b&gt;austin&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/austin.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * rpc add-flow {
 *     input {
 *         leaf flow-file {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf result {
 *             type string;
 *         }
 *     }
 *     status CURRENT;
 * }
 * &lt;/pre&gt;
 *
 */
public interface AustinService
    extends
    RpcService
{




    Future<RpcResult<AddFlowOutput>> addFlow(AddFlowInput input);

}

