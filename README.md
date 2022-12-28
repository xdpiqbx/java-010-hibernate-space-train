# java-010-hibernate-space-train

`@ManyToMany(fetch = FetchType.LAZY)` - (default) вытягивает данные только когда к ним явно обратится

`@ManyToMany(fetch = FetchType.EAGER)` - вытягивает все связаные данные

[Leaky abstraction](https://en.wikipedia.org/wiki/Leaky_abstraction)