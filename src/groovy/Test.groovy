package groovy;
import java.util.*;
import java.io.*;

import gate.*;
import gate.creole.*;
import gate.util.*;
import gate.util.persistence.PersistenceManager;
import gate.corpora.RepositioningInfo;

//Based on: http://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html
Runtime rt = Runtime.getRuntime();
String[] cmd = ["/home/ubuntu/Envs/grits_api_env/bin/python", "../simple_api/simple_server.py"]
Process proc = rt.exec(cmd);
proc.waitFor()
proc.getIn().eachLine {line ->
    println line
}
proc.getErr().eachLine {line ->
    println line
}
