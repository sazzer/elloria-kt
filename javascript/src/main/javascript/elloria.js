({
    baseUrl: '${project.basedir}/src/main/javascript',
    out: "${project.build.outputDirectory}/javascript/built/elloria.js",
    optimize: "none",
    paths: {
        Fluxxor: 'empty:',
        React: 'empty:',
        ReactBootstrap: 'empty:'
    },
    removeCombined: true,
    include: "main"
})
