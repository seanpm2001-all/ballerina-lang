/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.ballerinalang.util.repository;

import java.nio.file.Path;

/**
 * This class loads ballerina packages and files from the file system.
 *
 * @since 0.8.0
 */
public class FileSystemPackageRepository extends PackageRepository {
    private Path programDirPath;

    private BuiltinPackageRepository[] pkgRepositories;

    public FileSystemPackageRepository(Path programDirPath, BuiltinPackageRepository[] pkgRepositories) {
        this.programDirPath = programDirPath;
        this.pkgRepositories = pkgRepositories;
    }

    @Override
    public PackageSource loadPackage(Path packageDirPath) {
        for (BuiltinPackageRepository pkgRepository : pkgRepositories) {
            PackageSource packageSource = pkgRepository.loadPackage(packageDirPath);
            if (packageSource != null) {
                return packageSource;
            }
        }
        return loadPackageFromDirectory(packageDirPath, programDirPath);
    }

    @Override
    public PackageSource loadFile(Path filePath) {
        return loadFileFromDirectory(filePath, programDirPath);
    }
}
