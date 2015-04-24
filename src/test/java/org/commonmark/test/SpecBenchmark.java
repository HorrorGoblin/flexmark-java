package org.commonmark.test;

import org.commonmark.Parser;
import org.commonmark.html.HtmlRenderer;
import org.commonmark.spec.SpecExample;
import org.commonmark.spec.SpecReader;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@State(Scope.Benchmark)
public class SpecBenchmark {

    private static final List<String> SPEC_EXAMPLES = getSpecExamples();
    private static final String SPEC = SpecReader.readSpec();

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Benchmark
    public void parseAndRenderExamples() throws Exception {
        parseAndRender(SPEC_EXAMPLES);
    }

    @Benchmark
    public void parseAndRenderWholeSpec() {
        parseAndRender(Collections.singletonList(SPEC));
    }

    public void benchmarkOneOff() {
        List<String> examples = getSpecExamples();

        long expectedLength = parseAndRender(examples);
        long before = System.currentTimeMillis();
        long timedLength = parseAndRender(examples);
        long after = System.currentTimeMillis();

        if (timedLength != expectedLength) {
            throw new IllegalStateException("Woops?");
        }

        System.out.println("Parsed, then rendered " + timedLength + " characters in " +
                (after - before) + " ms");
    }

    private static List<String> getSpecExamples() {
        List<SpecExample> examples = SpecReader.readExamples();
        List<String> result = new ArrayList<>();
        for (SpecExample example : examples) {
            result.add(example.getSource());
        }
        return result;
    }

    private static long parseAndRender(List<String> examples) {
        long length = 0;
        for (String example : examples) {
            String result = HtmlRenderer.builder().build()
                    .render(Parser.builder().build().parse(example));
            length += result.length();
        }
        return length;
    }

}