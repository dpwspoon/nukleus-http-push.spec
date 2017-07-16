/**
 * Copyright 2016-2017 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.nukleus.http_push.streams;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.specification.nukleus.NukleusRule;

/**
 * RFC-6455, section 4.1 "Client-Side Requirements" RFC-6455, section 4.2
 * "Server-Side Requirements"
 */
public class ProxyConnectIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("streams", "org/reaktivity/specification/nukleus/http_push/streams/proxy/");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule()
            .directory("target/nukleus-itests");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @Specification({
        "${streams}/opening/connect/client",
        "${streams}/opening/connect/server"})
    public void shouldProxyConnection() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/inject.push.promise/connect/client",
        "${streams}/inject.push.promise/connect/server"})
    public void shouldInjectPushPromise() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/not.inject.push.promise.if.x.poll/connect/client",
        "${streams}/not.inject.push.promise.if.x.poll/connect/server"})
    public void notInjectPushPromiseIfXPollInjected() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/inject.header.values/connect/client",
        "${streams}/inject.header.values/connect/server"})
    public void shouldInjectHeaderValues() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/inject.missing.header.values/connect/client",
        "${streams}/inject.missing.header.values/connect/server"})
    public void shouldInjectMissingHeaderValues() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/strip.injected.headers/connect/client",
        "${streams}/strip.injected.headers/connect/server"})
    public void shouldStripInjectedHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/strip.injected.header.values/connect/client",
        "${streams}/strip.injected.header.values/connect/server"})
    public void shouldStripInjectedHeaderValues() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/strip.missing.injected.header.values/connect/client",
        "${streams}/strip.missing.injected.header.values/connect/server"})
    public void shouldStripMissingInjectedHeaderValues() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/proxy.post.request/connect/client",
        "${streams}/proxy.post.request/connect/server"})
    public void shouldProxyPostRequest() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/client.sent.abort/connect/client",
        "${streams}/client.sent.abort/connect/server"})
    public void shouldForwardAbortOnAcceptToConnect() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/server.sent.abort/connect/client",
        "${streams}/server.sent.abort/connect/server"})
    public void shouldForwardAbortOnConnectReplyToAcceptReply() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/server.sent.reset/connect/client",
        "${streams}/server.sent.reset/connect/server"})
    public void shouldForwardResetOnConnectToAccept() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }

    @Test
    @Specification({
        "${streams}/client.sent.reset/connect/client",
        "${streams}/client.sent.reset/connect/server"})
    public void shouldForwardResetOnAcceptReplyToConnectReply() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_PROXY");
        k3po.finish();
    }
}
