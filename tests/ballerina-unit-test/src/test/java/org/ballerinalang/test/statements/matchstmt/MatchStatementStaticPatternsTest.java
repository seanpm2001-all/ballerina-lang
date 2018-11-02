/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.ballerinalang.test.statements.matchstmt;

import org.ballerinalang.launcher.util.BAssertUtil;
import org.ballerinalang.launcher.util.BCompileUtil;
import org.ballerinalang.launcher.util.BRunUtil;
import org.ballerinalang.launcher.util.CompileResult;
import org.ballerinalang.model.values.BStringArray;
import org.ballerinalang.model.values.BValue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test cases to verify the behaviour of the static/constant value patterns with match statement in Ballerina.
 *
 * @since 0.985.0
 */
public class MatchStatementStaticPatternsTest {

    private CompileResult result, resultNegative, resultNegative2;

    @BeforeClass
    public void setup() {
        result = BCompileUtil.compile("test-src/statements/matchstmt/static-match-patterns.bal");
        resultNegative = BCompileUtil.compile("test-src/statements/matchstmt/static_match_patterns_negative.bal");
        resultNegative2 = BCompileUtil.
                compile("test-src/statements/matchstmt/unreachable_static_match_patterns_negative.bal");
    }

    @Test(description = "Test basics of static pattern match statement 1")
    public void testMatchStatementBasics1() {

        BValue[] returns = BRunUtil.invoke(result, "testStaticMatchPatternsBasic1", new BValue[]{});
        Assert.assertEquals(returns.length, 1);
        Assert.assertSame(returns[0].getClass(), BStringArray.class);

        BStringArray results = (BStringArray) returns[0];

        int i = -1;
        String msg = "Value is ";
        Assert.assertEquals(results.get(++i), msg + "'12'");
        Assert.assertEquals(results.get(++i), msg + "'Hello'");
        Assert.assertEquals(results.get(++i), msg + "'true'");
        Assert.assertEquals(results.get(++i), msg + "'15'");
        Assert.assertEquals(results.get(++i), msg + "'HelloAgain'");
        Assert.assertEquals(results.get(++i), msg + "'false'");
        Assert.assertEquals(results.get(++i), msg + "'Default'");
    }

    @Test(description = "Test basics of static pattern match statement 2")
    public void testMatchStatementBasics2() {

        BValue[] returns = BRunUtil.invoke(result, "testStaticMatchPatternsBasic2", new BValue[]{});
        Assert.assertEquals(returns.length, 1);
        Assert.assertSame(returns[0].getClass(), BStringArray.class);

        BStringArray results = (BStringArray) returns[0];

        int i = -1;
        String msg = "Value is ";
        Assert.assertEquals(results.get(++i), msg + "'12'");
        Assert.assertEquals(results.get(++i), msg + "'15 & HelloWorld'");
        Assert.assertEquals(results.get(++i), msg + "'HelloAgain & 34'");
        Assert.assertEquals(results.get(++i), msg + "'Default'");
        Assert.assertEquals(results.get(++i), msg + "'15 & 34'");
        Assert.assertEquals(results.get(++i), msg + "'true'");
    }

    @Test(description = "Test pattern will not be matched")
    public void testPatternNotMatched() {
        Assert.assertEquals(resultNegative.getErrorCount(), 51);
        int i = -1;
        String patternNotMatched = "pattern will not be matched";

        // simpleTypes
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 21, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 22, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 24, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 25, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 26, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 31, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 32, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 34, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 35, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 36, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 41, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 42, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 43, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 44, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 45, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 51, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 52, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 53, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 54, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 56, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 62, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 63, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 65, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 66, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 67, 9);

        // recordTypes
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 109, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 111, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 113, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 114, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 115, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 116, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 124, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 130, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 132, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 141, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 150, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 157, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 159, 9);

        // tupleTypes
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 168, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 169, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 170, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 171, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 172, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 174, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 175, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 190, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 196, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 203, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 206, 9);

        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 223, 9);
        BAssertUtil.validateError(resultNegative, ++i, patternNotMatched, 224, 9);
    }
    @Test(description = "Test unreachable pattern")
    public void testUnreachablePatterns() {
        Assert.assertEquals(resultNegative2.getErrorCount(), 8);
        int i = -1;
        String unreachablePatterm =
                "unreachable pattern: preceding patterns are too general or the pattern ordering is not correct";

        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 25, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 26, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 31, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 41, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 43, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 55, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 56, 9);
        BAssertUtil.validateError(resultNegative2, ++i, unreachablePatterm, 57, 9);
    }
}
