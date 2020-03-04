/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.langlib.map;

import org.ballerinalang.jvm.BallerinaErrors;
import org.ballerinalang.jvm.scheduling.Strand;
import org.ballerinalang.jvm.values.MapValue;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

import static org.ballerinalang.jvm.MapUtils.checkIsMapOnlyOperation;
import static org.ballerinalang.jvm.MapUtils.checkValidFieldForRecord;

/**
 * Extern function to remove element from the map if key exists.
 * ballerina.model.map:removeIfHasKey(string)
 */
@BallerinaFunction(
        orgName = "ballerina", packageName = "lang.map", functionName = "removeIfHasKey",
        args = {@Argument(name = "m", type = TypeKind.MAP), @Argument(name = "k", type = TypeKind.STRING)},
        returnType = {@ReturnType(type = TypeKind.ANY)},
        isPublic = true
)
public class RemoveIfHasKey {

    public static Object removeIfHasKey(Strand strand, MapValue<?, ?> m, String k) {
        String op = "removeIfHasKey()";

        checkIsMapOnlyOperation(m.getType(), op);
        checkValidFieldForRecord(m, k, op);
        try {
            return m.remove(k);
        } catch (org.ballerinalang.jvm.util.exceptions.BLangFreezeException e) {
            throw BallerinaErrors.createError(e.getMessage(),
                    "Failed to remove element from map: " + e.getDetail());
        }
    }
}
