/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.fabric.zookeeper.commands;

import java.net.URL;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.linkedin.zookeeper.client.IZKClient;

@Command(name = "set", scope = "zk", description = "Set a node's data")
public class Set extends ZooKeeperCommandSupport {

    @Option(name = "-i", aliases = {"--import"}, description = "Import data from an url")
    boolean importUrl;

    @Argument(description = "Path of the node to set", index = 0)
    String path;

    @Argument(description = "The new data, or url if 'import' option is used", index = 1)
    String data;

    @Override
    protected void doExecute(IZKClient zk) throws Exception {

        String nodeData = data;

        if (importUrl) {
            nodeData = loadUrl(new URL(data));
        }

        zk.setData(path, nodeData);
    }
}
