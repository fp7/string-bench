package com.github.fp7;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.CompilerControl.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(org.openjdk.jmh.annotations.Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class MyBenchmark {

  @Param({"mac os x", "linux"})
  private String osName;


  @Benchmark
  public void contains(Blackhole bh) {
    bh.consume(osName.contains("mac"));
  }

  @Benchmark
  public void indexof(Blackhole bh) {
    bh.consume(osName.indexOf("mac") >= 0);
  }


  @Benchmark
  @CompilerControl(Mode.EXCLUDE)
  public void interpretated_index_of(Blackhole bh) {
    bh.consume(osName.indexOf("mac") >= 0);
  }

  @Benchmark
  @CompilerControl(Mode.EXCLUDE)
  public void interpretated_contains(Blackhole bh) {
    bh.consume(osName.contains("mac"));
  }
}
