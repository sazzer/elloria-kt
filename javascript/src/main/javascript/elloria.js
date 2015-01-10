({
    /*
     * By default, all modules are located relative to this path. If baseUrl
     * is not explicitly set, then all modules are loaded relative to
     * the directory that holds the build file. If appDir is set, then
     * baseUrl should be specified as relative to the appDir.
     */
    baseUrl: '${project.basedir}/src/main/javascript',
    /*
     * The directory path to save the output. If not specified, then
     * the path will default to be a directory called "build" as a sibling
     * to the build file. All relative paths are relative to the build file.
     */
    dir: "${project.build.outputDirectory}/javascript",
    /*
     *  How to optimize all the JS files in the build output directory.
     *  Right now only the following values
     *  are supported:
     *  - "uglify": (default) uses UglifyJS to minify the code.
     *  - "closure": uses Google's Closure Compiler in simple optimization
     *  mode to minify the code. Only available if running the optimizer using
     *  Java.
     *  - "closure.keepLines": Same as closure option, but keeps line returns
     *  in the minified files.
     *  - "none": no minification will be done.
     */
    optimize: "none",
    /*
     *  Set paths for modules. If relative paths, set relative to baseUrl above.
     *  If a special value of "empty:" is used for the path value, then that
     *  acts like mapping the path to an empty file. It allows the optimizer to
     *  resolve the dependency to path, but then does not include it in the output.
     *  Useful to map module names that are to resources on a CDN or other
     *  http: URL when running in the browser and during an optimization that
     *  file should be skipped because it has no dependencies.
     */
    paths: {
    },
    
    /*
     * If set to true, any files that were combined into a build layer will be
     * removed from the output folder.
     */
    removeCombined: true,
    
    modules: [
        {
            name: "main"
        }
    ]
})
