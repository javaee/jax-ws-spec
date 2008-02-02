/**
   Provides HTTP SPI that is used for portable deployment of JAX-WS
   web services in containers(for e.g. servlet containers). This SPI
   is not for end developers but provides a way for the container
   developers to deploy JAX-WS services portably.

   <p>
   The portable deployment happens in three steps:
   <ol>
   <li>Container needs to create {@link javax.xml.ws.spi.http.HttpContext} from the
   deployment. For example, a HttpContext could be created using
   ServletContext and url-pattern for the web service in servlet container case.
   <li>It creates a {@link javax.xml.ws.Endpoint} using web service deployment
   descriptor files.
   <li>Then publishes the endpoint using {@link javax.xml.ws.Endpoint#publish(HttpContext)}
   </ol>
   <p>A sample JAX-WS deployment can be done as follows:
   <blockquote><pre>
   // Create service
   // Endpoint endpoint = Provider.provider().createEndpoint(bindingId, impl, invoker, mtomFeature, ...)
   Endpoint endpoint = Endpoint.create(impl, mtomFeature, ...)
   // If DD overrides wsdl:service, wsdl:port names, configure them
   endpoint.setProperties(...)
   // Configure service with the packaged WSDL, Schema metadata(if any)
   endpoint.setMetadata(...)
   // Configure the handler chain from DD
   endpoint.getBinding().setHandlerChain(...)
   // Finally endpoint is ready to accept requests
   endpoint.publish(httpContext)
   </blockquote></pre>

   <p>
   During Endpoint.publish(HttpContext), JAX-WS runtime registers
   {@link javax.xml.ws.spi.http.HttpHandler} callback with {@link javax.xml.ws.spi.http.HttpContext}.
   JAX-WS runtimes must implement the {@link javax.xml.ws.spi.http.HttpHandler} interface and
   this interface provides a callback that is invoked by containers
   to handle incoming requests from clients. A HTTP request and its
   response is known as an exchange. HTTP exchanges are represented
   by the {@link javax.xml.ws.spi.http.HttpExchange} class.

   The {@link javax.xml.ws.spi.http.HttpExchange} class encapsulates everything an
   application needs to process incoming requests and to generate
   appropriate responses.
   <p>
   A sample JAX-WS {@link javax.xml.ws.spi.http.HttpHandler} and its registration with
   the {@link javax.xml.ws.spi.http.HttpContext} is shown below:
   <blockquote><pre>
   httpContext.setHandler(new HttpHandler() {
       public void handle(HttpExchange t) throws IOException {
           InputStream is = t.getRequestBody();
           read(is); // .. read the request body
           is.close();
           // Invoke web service from the request, and send the response
           ...
           t.sendResponseHeaders(200, response.length());
           OutputStream os = t.getResponseBody();
           os.write(response.getBytes());
           os.close();
           t.close();
       }
   });
   </blockquote></pre>

   <p>
   A sample JAX-WS undeployment can be done as follows:
   <p>
   <blockquote><pre>
   endpoint.stop();
   </blockquote></pre>

   @author Jitendra Kotamraju
   @since 2.2
 */
package javax.xml.ws.spi.http;
