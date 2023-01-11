/*
 * Copyright (C) 2022, 2023, THL A29 Limited, a Tencent company. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class MultiVersion {
    public static void main(String args[]) {
        int type = 0;
        int type2 = 0;
        int type3 = 0;
        if (args.length > 0) {
            type = Integer.parseInt(args[0]);
            if (args.length > 1) {
                type2 = Integer.parseInt(args[1]);
                if (args.length > 2) {
                    type3 = Integer.parseInt(args[2]);
                }
            }
        }
        int total = 0;
        for (int i = 0; i < 100000;i++) {
            total += foo(type);
        }
        for (int i = 0; i < 100000; i++) {
            total += foo(type2);
        }
        for (int i = 0; i < 100000; i++) {
            total += foo(type3);
        }
        System.out.println(total);
    }

    private static int foo(int type) {
        if (type == 0) {
            return 1;
        } else if (type > 0) {
            return 2;
        } else {
            return 3;
        }
    }
}
