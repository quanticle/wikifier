# wikifier

A small binary that takes markdown files and formats them for PMWiki.

Wikifier assumes that your markdown file starts with a top-level heading (e.g. `# Title`) which it can use as the title of the wiki page. Wikifier turns that into a PMWiki `(:title:)` directive. It also adds the `(:mathjax:)` directive and reformats `$mathjax$` into `{$mathjax$}`.

## Building

1. Install [Leiningen](https://leiningen.org/)
2. `lein bin` -> this will create `targets/default/wikifier`, which is the self-contained binary you can copy to someplace on your path
