<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<form action="testsyncmasterdatasubmit.htm" method="post">
<label >URI</label><input type="text"  id="strurl" name="strurl" value="${strurl}" style="width: 425px;" />
<input type="submit" value=" Submit " >
<div align="left" style="width:100% float: left" > 
<div style="width:49%; float: left;">
<label style="display: block;" >Request XML</label>
<c:if test="${strxml!=null}">
<textarea rows="25" name="strxml" style="width:100%" >
${strxml}
</textarea>
</c:if>
<c:if test="${strxml==null}">
<textarea rows="25" name="strxml" style="width:100%" >
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bar="http://www.excise.go.th/xsd/barcode">
   <soapenv:Header/>
   <soapenv:Body>
      <bar:EbarcodeSyncMasterDataRequest>
         <InternetUser>
            <CompanyId>0105529039293</CompanyId>
            <CompanyUserId>tip1</CompanyUserId>
            <CompanyUserPwd>tip1</CompanyUserPwd>
         </InternetUser>
      </bar:EbarcodeSyncMasterDataRequest>
   </soapenv:Body>
</soapenv:Envelope>
</textarea>
</c:if>
</div>
<div style="width:49%; float: left;">
<label style="display: block;" >Response XML</label>
<textarea rows="25" cols="56" name="strxmlrt" style="width:100%" >
${strxmlrt}
</textarea>
</div>
<br>

</div>

</form>
<input type="button" value="Back" onclick="window.location.href='menuTestWS.htm'">