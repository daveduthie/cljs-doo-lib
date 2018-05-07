# Minimal CLJS test

A minimal example of using doo as a [library](https://github.com/bensu/doo#library) to test ClojureScript code.

```
.
├── README.md
├── deps.edn
├── dir.html
├── dir.txt
├── package.json
├── src
│   └── cljs_hi
│       └── core.cljs
└── test
    └── cljs_hi
        ├── core_test.cljs
        ├── main.clj
        └── runner.cljs

```

## Set up

- Clone this repo
- Install the Clojure [command line tools](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
- Likewise for [Node.js](https://nodejs.org/en/)
- Install NPM deps: `npm install`

## Run it

You can run `clj -A:test -m cljs-hi.main` to compile the test runner as the entrypoint and run the tests.

To start incremental recompilation, run `clj -A:test -m cljs-hi.main watch`, then, in another terminal, run `clj -A:test -m cljs-hi.main test`.
