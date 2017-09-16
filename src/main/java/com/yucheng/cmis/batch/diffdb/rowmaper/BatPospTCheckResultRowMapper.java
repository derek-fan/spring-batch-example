package com.yucheng.cmis.batch.diffdb.rowmaper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yucheng.cmis.batch.diffdb.pojo.BatPospTCheckResult;



public class BatPospTCheckResultRowMapper implements RowMapper<BatPospTCheckResult> {

	public BatPospTCheckResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		BatPospTCheckResult obj = new BatPospTCheckResult();
		obj.setEventid(rs.getString("EVENTID"));
		obj.setSaTranPlace(rs.getString("SA_TRAN_PLACE"));
		obj.setSaTranPlace2(rs.getString("SA_TRAN_PLACE2"));
		obj.setSaInstId(rs.getString("SA_INST_ID"));
		obj.setSaBatchDay(rs.getString("SA_BATCH_DAY"));
		obj.setSaHostDay(rs.getString("SA_HOST_DAY"));
		obj.setSaSettleDay(rs.getString("SA_SETTLE_DAY"));
		obj.setSaSysTraNum(rs.getString("SA_SYS_TRA_NUM"));
		obj.setSaRtrRefNum(rs.getString("SA_RTR_REF_NUM"));
		obj.setSaTxnDateTime(rs.getString("SA_TXN_DATE_TIME"));
		obj.setSaMchtCd(rs.getString("SA_MCHT_CD"));
		obj.setSaTermId(rs.getString("SA_TERM_ID"));
		obj.setSaPriAcctNum(rs.getString("SA_PRI_ACCT_NUM"));
		obj.setSaSelfFlg(rs.getString("SA_SELF_FLG"));
		obj.setSaMsgType(rs.getString("SA_MSG_TYPE"));
		obj.setSaProcCode(rs.getString("SA_PROC_CODE"));
		obj.setSaPosConCd(rs.getString("SA_POS_CON_CD"));
		obj.setSaBalacctStatus(rs.getString("SA_BALACCT_STATUS"));
		obj.setSaSettleStatus(rs.getString("SA_SETTLE_STATUS"));
		obj.setSaIssBrc(rs.getString("SA_ISS_BRC"));
		obj.setSaAcqBrc(rs.getString("SA_ACQ_BRC"));
		obj.setSaCanclFlag(rs.getString("SA_CANCL_FLAG"));
		obj.setSaAcqIns(rs.getString("SA_ACQ_INS"));
		obj.setSaFwdIns(rs.getString("SA_FWD_INS"));
		obj.setSaCdFlag(rs.getString("SA_CD_FLAG"));
		obj.setSaErrDate(rs.getString("SA_ERR_DATE"));
		obj.setSaHostSerial(rs.getString("SA_HOST_SERIAL"));
		obj.setSaBalerrSerial(rs.getString("SA_BALERR_SERIAL"));
		obj.setSaUpdatetime(rs.getString("SA_UPDATETIME"));
		obj.setSaTellercode(rs.getString("SA_TELLERCODE"));
		obj.setSaTranPlace3(rs.getString("SA_TRAN_PLACE3"));
		obj.setSaTrandevtype(rs.getString("SA_TRANDEVTYPE"));
		obj.setSaTrankind(rs.getString("SA_TRANKIND"));
		obj.setSaCardtrantype(rs.getString("SA_CARDTRANTYPE"));
		obj.setSaT0Flag(rs.getString("SA_T0_FLAG"));
		obj.setiCmpNo(rs.getInt("I_CMP_NO"));
		obj.setSaTxnAmount(rs.getBigDecimal("SA_TXN_AMOUNT"));
		obj.setSaChargeFee(rs.getBigDecimal("SA_CHARGE_FEE"));
		obj.setSaPayFee(rs.getBigDecimal("SA_PAY_FEE"));
		obj.setSaMerchFee(rs.getBigDecimal("SA_MERCH_FEE"));
		obj.setSaSelfMercFee(rs.getBigDecimal("SA_SELF_MERC_FEE"));
		obj.setSaCustomerFee(rs.getBigDecimal("SA_CUSTOMER_FEE"));
		obj.setdErrAmount(rs.getBigDecimal("D_ERR_AMOUNT"));
		obj.setcErrAmount(rs.getBigDecimal("C_ERR_AMOUNT"));
		obj.setSaAcqBrcFee(rs.getBigDecimal("SA_ACQ_BRC_FEE"));
		obj.setdFeeAmount(rs.getBigDecimal("D_FEE_AMOUNT"));
		obj.setcFeeAmount(rs.getBigDecimal("C_FEE_AMOUNT"));
		obj.setSaSav1(rs.getBigDecimal("SA_SAV1"));
		obj.setSaSav2(rs.getBigDecimal("SA_SAV2"));
		obj.setSaSav3(rs.getString("SA_SAV3"));
		obj.setSaSav4(rs.getString("SA_SAV4"));
		obj.setSaSav5(rs.getString("SA_SAV5"));
		obj.setSaFrntDate(rs.getString("SA_FRNT_DATE"));
		obj.setEtlname(rs.getString("ETLNAME"));
		obj.setDatadate(rs.getString("DATADATE"));
		
		
		return obj;
	}
}
