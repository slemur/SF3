package serp.bytecode.visitor;

import java.io.*;

import serp.bytecode.*;
import serp.bytecode.lowlevel.*;

/**
 * Visitor type that outputs a detailed, formatted document of the
 * visited entity; similar to the <i>javap -c</i> command but more detailed.
 *
 * @author Abe White
 */
public class PrettyPrintVisitor extends BCVisitor {
    private PrintWriter _out = null;
    private String _prefix = "";

    /**
     * Constructor; all pritning will go to stdout.
     */
    public PrettyPrintVisitor() {
        _out = new PrintWriter(System.out);
    }

    /**
     * Constructor.
     *
     * @param out the stream to print to
     */
    public PrettyPrintVisitor(PrintWriter out) {
        _out = out;
    }

    /**
     * Invoke with the class or file names to pretty print; the
     * functionality is similar to the <i>javap -c</i> command, but more
     * detailed.
     */
    public static void main(String[] args)
        throws ClassNotFoundException, IOException {
        if (args.length == 0) {
            System.err.println("Usage: java " 
                + PrettyPrintVisitor.class.getName() 
                + " <class name | .class file>+");
            System.exit(1);
        }

        PrettyPrintVisitor ppv = new PrettyPrintVisitor();
        Project project = new Project();
        BCClass type;
        for (int i = 0; i < args.length; i++) {
            if (args[i].endsWith(".class"))
                type = project.loadClass(new File(args[i]));
            else
                type = project.loadClass(Class.forName(args[i], false, 
                    PrettyPrintVisitor.class.getClassLoader()));
            ppv.visit(type);
        }
    }

    @Override
    public void enterBCMethod(BCMethod method) {

    }
}
