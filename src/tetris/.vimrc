echo 'Tetris Project'
set noautochdir
let &makeprg='ant -buildfile ../../build.xml run'
let g:syntastic_java_javac_classpath = "../../build/classes"
