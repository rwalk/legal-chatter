# legal-chatter
Transcribing oral arugments via sphinx4

The [free law project](https://free.law/) has made available well over a year of oral arguments in MP3 format ([announcement](https://free.law/2014/10/31/announcing-oral-arguments-on-courtlistener/).  Imagine the fascinating things that can be done with this data!  But the biggest challenge is that none of this information has been transcribed.

This project is a tiny start at an attempt to transcribe some of this audio using [Sphinx4](https://github.com/cmusphinx/sphinx4).

More to come soon, I hope :)

# Building/Running
Build:
```
./gradlew build
```
Run (uses the gradle application plugin):
```
./gradlew run -q -Dexec.args="<your-file>"
```


