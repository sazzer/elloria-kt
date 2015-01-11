({
    baseUrl: '${project.basedir}/src/main/javascript',
    out: "${project.build.outputDirectory}/javascript/built/elloria.js",
    optimize: "none",
    paths: {
        React: 'empty:',
        ReactBootstrap: 'empty:'
    },
    removeCombined: true,
    include: "main"
})
