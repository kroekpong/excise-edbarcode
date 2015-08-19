<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<form action="testsyncmasterdatasubmit.htm" method="post">
<input type="text" name="strurl" value="${strurl}" style="width: 425px;" />
<div align="left">
<c:if test="${strxml!=null}">
<textarea rows="25" cols="58" name="strxml">
${strxml}
</textarea>
</c:if>
<c:if test="${strxml==null}">
<textarea rows="25" cols="58" name="strxml">
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:bar="http://www.excise.go.th/xsd/barcode">
   <soapenv:Header/>
   <soapenv:Body>
      <bar:EbarcodeSyncMasterDataRequest>
         <InternetUser>
            <CompanyId>?</CompanyId>
            <CompanyUserId>?</CompanyUserId>
            <CompanyUserPwd>?</CompanyUserPwd>
         </InternetUser>
      </bar:EbarcodeSyncMasterDataRequest>
   </soapenv:Body>
</soapenv:Envelope>
</textarea>
</c:if>
<textarea rows="25" cols="56" name="strxmlrt">
${strxmlrt}
</textarea>
<br>
<input type="submit" value="Submit">
</form>
</div>