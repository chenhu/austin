package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;austin&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/austin.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container input {
 *     leaf flow-file {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;austin/add-flow/input&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowInputBuilder
 *
 */
public interface AddFlowInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.austin.rev150105.AddFlowInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:austin","2015-01-05","input"));

    java.lang.String getFlowFile();

}

