def call(String urlStr, Map headers = [:]) {
    def url = new URL(urlStr)
    def connection = url.openConnection()
    connection.requestMethod = 'GET'
    headers.each { k, v -> connection.setRequestProperty(k, v) }
    connection.connect()
    int code = connection.responseCode
    def stream = (code >= 200 && code < 400) ? connection.inputStream : connection.errorStream
    def response = stream?.text
    println(response)
    def tmpDir = System.getProperty('java.io.tmpdir')
    def aFile = new File(tmpDir, 'http_get_output.txt')
    println "Attempting to write debug output to: ${aFile.getAbsolutePath()}"
    aFile.withWriter('utf-8') { writer ->
        writer.writeLine('this is a test content')
    }
    [status: code, body: response]
}
