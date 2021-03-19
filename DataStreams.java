
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.EOFException;

import java.util.ArrayList;

public class DataStreams {

    
    
    public static void writeFile(String fileName, ArrayList<Term> myTerms) throws IOException {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> defs = new ArrayList<String>();
        ArrayList<Integer> knowledges = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> hints = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> aliases = new ArrayList<ArrayList<String>>();

        for (Term t : myTerms)
        {
            words.add(t.getDisp());
            defs.add(t.getDefinition());
            knowledges.add(t.getKnowledge());
            hints.add(t.getHints());
            aliases.add(t.getAliases());
        }

        ArrayList<String> hintsSplit = new ArrayList<String>();
        for (ArrayList<String> group : hints)
        {
            hintsSplit.add(String.join(",", group));

        }

        ArrayList<String> aliasesSplit = new ArrayList<String>();
        for (ArrayList<String> group : aliases)
        {
            aliasesSplit.add(String.join(",", group));

        }

        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new
                BufferedOutputStream(new FileOutputStream(fileName)));

            for (int i = 0; i < words.size(); i ++) {
                out.writeUTF(words.get(i));
                out.writeUTF(defs.get(i));
                out.writeInt(knowledges.get(i));
                out.writeUTF(hintsSplit.get(i));
                out.writeUTF(aliasesSplit.get(i));
            }
        } finally {
            out.close();
        }

    }
    
    
    public static void readFile(String fileName) throws IOException {
        DataInputStream in = null;
        double total = 0.0;
        try {

            in = new DataInputStream(new
                BufferedInputStream(new FileInputStream(fileName)));

            double price;
            int unit;
            String desc;

            try {
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n",
                        unit, desc, price);
                    total += unit * price;
                }
            } catch (EOFException e) { 
            System.out.format("For a TOTAL of: $%.2f%n", total);}
        }
        finally {
            in.close();
        }
    }
}