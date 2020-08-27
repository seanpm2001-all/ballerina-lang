// Copyright (c) 2020 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

int[] idata = [1, 2, 3];
string[] sdata = ["A", "B", "C"];
float[] fdata = [10.0, 11.0, 12.0];

string output = "";

function concatIntString(int i, string s) {
    output = output + i.toString() + ":" + s + " ";
}

function concatIntStringFloat(int i, string s, float f) {
    output = output + i.toString() + ":" + s + ":" + f.toString() + " ";
}

// ---------------------------------------------------------------------------------------------------------------------

function test2LevelNestedForeachWithoutType() returns string {
    output = "";
    foreach var i in idata {
        foreach var s in sdata {
            concatIntString(i, s);
        }
    }
    return output;
}

function test2LevelNestedForeachWithType() returns string {
    output = "";
    foreach int i in idata {
        foreach string s in sdata {
            concatIntString(i, s);
        }
    }
    return output;
}

// ---------------------------------------------------------------------------------------------------------------------

function test3LevelNestedForeachWithoutType() returns string {
    output = "";
    foreach var i in idata {
        foreach var s in sdata {
            foreach var f in fdata {
                concatIntStringFloat(i, s, f);
            }
        }
    }
    return output;
}

function test3LevelNestedForeachWithType() returns string {
    output = "";
    foreach int i in idata {
        foreach string s in sdata {
            foreach float f in fdata {
                concatIntStringFloat(i, s, f);
            }
        }
    }
    return output;
}

function testNestedForeachWithBreak1() returns string {
    string result = "";

    string[] sdata1 = ["ballerina", "ballerinaSamples"];
    string[] sdata2 = ["ballerinaSamples"];

    boolean status = false;
    foreach var s1 in sdata1 {
        foreach var s2 in sdata2 {
            if (s1 == s2) {
                status = true;
                result = result + "inner";
                break;
            }
        }
        if (status) {
            result = result + "outer";
            break;
        }
    }

    return result;
}

function testNestedForeachWithBreak2() returns string {
    string result = "";

    string[] sdata1 = ["A", "B", "C"];
    string[] sdata2 = ["D", "E", "F"];
    string[] sdata3 = ["ballerina", "ballerinaSamples"];
    string[] sdata4 = ["ballerinaSamples"];

    boolean status = false;
    foreach var s1 in sdata1 {
        foreach var s2 in sdata2 {
            foreach var s3 in sdata3 {
                foreach var s4 in sdata4 {
                    if (s3 == s4) {
                        status = true;
                        result = result + "level4";
                        break;
                    }
                }
                if (status) {
                    result = result + "level3";
                    break;
                }
            }
            if (status) {
                result = result + "level2";
                break;
            }
        }
        if (status) {
            result = result + "level1";
            break;
        }
    }

    return result;
}
