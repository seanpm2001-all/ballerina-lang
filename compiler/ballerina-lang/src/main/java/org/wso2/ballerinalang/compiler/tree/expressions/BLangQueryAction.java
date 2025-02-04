/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.ballerinalang.compiler.tree.expressions;

import org.ballerinalang.model.tree.NodeKind;
import org.ballerinalang.model.tree.statements.QueryActionNode;
import org.wso2.ballerinalang.compiler.tree.BLangNode;
import org.wso2.ballerinalang.compiler.tree.BLangNodeAnalyzer;
import org.wso2.ballerinalang.compiler.tree.BLangNodeTransformer;
import org.wso2.ballerinalang.compiler.tree.BLangNodeVisitor;
import org.wso2.ballerinalang.compiler.tree.clauses.BLangDoClause;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code BLangQueryAction} represents the do action  in Ballerina.
 *
 * @since 1.2.0
 */
public class BLangQueryAction extends BLangExpression implements QueryActionNode {

    // BLangNodes
    public List<BLangNode> queryClauseList = new ArrayList<>();
    public BLangDoClause doClause;

    @Override
    public void accept(BLangNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> void accept(BLangNodeAnalyzer<T> analyzer, T props) {
        analyzer.visit(this, props);
    }

    @Override
    public <T, R> R apply(BLangNodeTransformer<T, R> modifier, T props) {
        return modifier.transform(this, props);
    }

    @Override
    public NodeKind getKind() {
        return NodeKind.DO_ACTION;
    }

    @Override
    public List<BLangNode> getQueryClauses() {
        return queryClauseList;
    }

    @Override
    public void addQueryClause(BLangNode queryClause) {
        this.queryClauseList.add(queryClause);
    }

    @Override
    public BLangDoClause getDoClause() {
        for (BLangNode clause : queryClauseList) {
            if (clause.getKind() == NodeKind.DO) {
                return (BLangDoClause) clause;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return queryClauseList.stream().map(BLangNode::toString).collect(Collectors.joining("\n"));
    }
}
