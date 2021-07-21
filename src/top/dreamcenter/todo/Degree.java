package top.dreamcenter.todo;

public enum Degree {
    IGNORE,     // still run but without any warn
    TIP,        // still run and with a tip first
    SKIP,       // stop run but give a tip
    STOP        // stop run and then throw an exception
}
