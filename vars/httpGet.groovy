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
    [status: code, body: response]
}
