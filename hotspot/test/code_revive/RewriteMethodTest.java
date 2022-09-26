/*
 *
 * Copyright (C) 2022 THL A29 Limited, a Tencent company. All rights reserved.
 * DO NOT ALTER OR REMOVE NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation. THL A29 Limited designates
 * this particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License version 2 for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
/*
 * @test
 * @summary Test whether inline method is in dependency array when revive is on
 * @library /testlibrary
 * @build RedefineClassHelper
 * @run main RedefineClassHelper
 * @compile test-classes/RedefineDoubleDelete.java
 * @run main/othervm RewriteMethodTest
 */

import com.oracle.java.testlibrary.OutputAnalyzer;
import com.oracle.java.testlibrary.ProcessTools;


public class RewriteMethodTest {
    public static void main(String[] args) throws Exception {
        // build The app
        String[] appClass = new String[] {"RedefineDoubleDelete"};

        // dump aot
        OutputAnalyzer output = TestCommon.testDump(appClass, "log=archive=trace",
                                                    "-XX:-TieredCompilation",
                                                    "-Xcomp",
                                                    "-XX:-BackgroundCompilation",
                                                    "-javaagent:redefineagent.jar",
                                                    "-XX:CompileOnly=RedefineDoubleDelete$B.faa");
        System.out.println(output.getOutput());
        output.shouldHaveExitValue(0);

        output = TestCommon.testRunWithAOT(appClass, "log=revive=fail",
                                                    "-XX:-TieredCompilation",
                                                    "-Xcomp",
                                                    "-XX:-BackgroundCompilation",
                                                    "-javaagent:redefineagent.jar",
                                                    "-XX:CompileOnly=RedefineDoubleDelete$B.faa");
        System.out.println(output.getOutput());
        output.shouldHaveExitValue(0);
    }
}
