<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery 주석처리 -->
<!--     <script src="/resources/vendor/jquery/jquery.min.js"></script> -->

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="/resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="/resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="/resources/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <!-- 모바일 크기가 됐을 때 메뉴가 펼쳐지는 문제를 해결하기 위해 추가한 코드 -->
<script>
  $(document).ready(function() {
    $('#dataTables-example').DataTable({
      responsive: true
    });
    $(".sidebar-nav")
      .attr("class","sidebar-nav navbar-collapse collapse")
      .attr("aria-expanded",'false')
      .attr("style","height:1px");
  });
</script>

</body>

</html>
