({
    baseUrl: '${project.basedir}/src/main/javascript',
    out: "${project.build.outputDirectory}/javascript/built/elloria.js",
    optimize: "none",
    paths: {
    },
    removeCombined: true,
    include: "main"
})
