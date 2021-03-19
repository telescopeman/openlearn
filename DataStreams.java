
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
import java.util.Arrays;

public class DataStreams {

    
    public void writeFile(String fileName, ArrayList<Term> myTerms) throws IOException {
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
                
                out.writeDouble(1.0);
                
                out.writeUTF(defs.get(i));
                
                out.writeDouble(1.0);
                
                
                out.writeInt(knowledges.get(i));
                
                out.writeDouble(1.0);
                
                out.writeUTF(hintsSplit.get(i));
                
                out.writeDouble(1.0);
                
                out.writeUTF(aliasesSplit.get(i));
                
                out.writeDouble(1.0);
            }
        } finally {
            out.close();
        }

        
    }

    public ArrayList<Term> readFile(String fileName) throws IOException {
        DataInputStream in = null;
        double total = 0.0;
        in = new DataInputStream(new
            BufferedInputStream(new FileInputStream(fileName)));

               
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> defs = new ArrayList<String>();
        ArrayList<Integer> knowledges = new ArrayList<Integer>();
        ArrayList<String> hintsSplit = new ArrayList<String>();
        ArrayList<String> aliasesSplit = new ArrayList<String>();
        try {

            
            

            try {
                while (true) {
                    words.add(in.readUTF());
                    
                    in.readDouble();
                    
                    defs.add(in.readUTF());
                    
                    in.readDouble();
                    
                    knowledges.add(in.readInt());
                    
                    in.readDouble();
                    
                    hintsSplit.add(in.readUTF());
                    
                    in.readDouble();
                    
                    aliasesSplit.add(in.readUTF());
                    
                    in.readDouble();
                }

            } catch (EOFException e) { 
                System.out.format("For a TOTAL of: $%.2f%n", total);}
        }
        finally {
            
            ArrayList<ArrayList<String>> hints = new ArrayList<ArrayList<String>>();
            for (String str : hintsSplit)
            {
                ArrayList<String> spl = Arrays.asList(str.split(","));
                hints.addAll((spl));
            }
            
            ArrayList<ArrayList<String>> aliases = new ArrayList<ArrayList<String>>();
            for (String str : aliasesSplit)
            {
                aliases.addAll(Arrays.asList(str.split(",")));
            }
                
            
            int i = 0;
            
            ArrayList<Term> listOfTerms = new ArrayList<Term>();
            for (String word : words)
            {
                Term ter = new Term(word,defs.get(i),aliases.get(i));
                ter.addHints(hints.get(i));
                ter.setKnowledge(knowledges.get(i));
                listOfTerms.add(ter);
                i++;
            }
            
            
            in.close();
            
            return listOfTerms;
        }
    }
}