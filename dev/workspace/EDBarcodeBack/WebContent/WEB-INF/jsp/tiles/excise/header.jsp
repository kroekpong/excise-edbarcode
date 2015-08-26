	<%@ page pageEncoding="UTF-8"%>

 
<header class="global">
<section class="jumbotron no-image">
  <nav class="main-nav">
    <a href="/" class="ident"><span>Baiwa</span></a>
      <div class="dropdown">
        <a class="trigger" href="#"></a>
        <ul class="drop-content">      
          <li><a href="<%=request.getContextPath()%>/newDownloadPage.htm">ดาวน์โหลด</a></li>
          <li><a href="<%=request.getContextPath()%>/newDemoForm.htm">รายการใหม่</a></li>
          <li><a href="<%=request.getContextPath()%>/ws/EDBarcodeService.wsdl">WSDL</a></li>
          <li><a href="<%=request.getContextPath()%>/testws/main.htm">TestWS</a></li>
          <li><a href="#">Logging</a></li>
        </ul>
      </div>
  </nav>
  </section>
</header>
 