/**

Copyright 2012 University of Stuttgart

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at 

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

**/

package eu.optimis.mi.monitoring_manager.queries;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import eu.optimis.mi.monitoring_resources.MonitoringResourceDataset;
import eu.optimis.mi.monitoring_resources.MonitoringResourceDatasets;

public class DateTypeLevelQuery {

	public static String dateformat = "yyyy-MM-dd HH:mm:ss";

	public static MonitoringResourceDatasets getResource_type_all(
			Connection conn, String resourceType, java.util.Date dfrom,
			java.util.Date dto) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		String sfrom = sdf.format(dfrom);
		String sto = sdf.format(dto);

		String sqlStatement = null;
		
		if (resourceType.contains("service")){
		sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_service "
				+ "WHERE metric_timestamp BETWEEN '"
				+ sfrom
				+ "' AND '"
				+ sto
				+ "'";
		}
		else if (resourceType.contains("virtual")){
		sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_virtual "
				+ "WHERE metric_timestamp BETWEEN '"
				+ sfrom
				+ "' AND '"
				+ sto
				+ "'";
		}
		else if (resourceType.contains("physical")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_physical "
					+ "WHERE metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto
					+ "'";
			}
		else if (resourceType.contains("energy")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_energy "
					+ "WHERE metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto
					+ "'";
			}
		else return new MonitoringResourceDatasets();
		MonitoringResourceDatasets msets = new MonitoringResourceDatasets();
		List<MonitoringResourceDataset> list = new ArrayList<MonitoringResourceDataset>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlStatement);
			while (rs.next()) {
				Timestamp sp = rs.getTimestamp("metric_timestamp");
				Date date = new Date(sp.getTime());
				MonitoringResourceDataset ms = new MonitoringResourceDataset(rs
						.getString("service_resource_Id"), rs
						.getString("virtual_resource_id"), rs
						.getString("physical_resource_id"), resourceType, rs
						.getString("monitoring_information_collector_id"), rs
						.getString("metric_name"),
						rs.getString("metric_value"), rs
								.getString("metric_unit"), date);
				list.add(ms);
				date = null;
			}
			msets.setMonitoring_resource(list);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage() + ":"
					+ e.getSQLState());
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			;
		}
		return msets;

	}

	public static MonitoringResourceDatasets getResource_type_tid(
			Connection conn, String resourceType, String tId,
			java.util.Date dfrom, java.util.Date dto) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		String sfrom = sdf.format(dfrom);
		String sto = sdf.format(dto);

		String sqlStatement = null;
		if (resourceType.contains("service"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_service "
					+ "WHERE physical_resource_id='"
					+ tId
					+ "' AND metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto + "'";
		else if (resourceType.contains("virtual"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_virtual "
					+ "WHERE virtual_resource_id='"
					+ tId
					+ "' AND metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto + "'";
		else if (resourceType.contains("physical"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_physical "
					+ "WHERE service_resource_id='"
					+ tId
					+ "' AND metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto + "'";
		else if (resourceType.contains("energy"))
		sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
			+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_energy "
			+ "WHERE physical_resource_id='"
			+ tId
			+ "' AND metric_timestamp BETWEEN '"
			+ sfrom
			+ "' AND '"
			+ sto + "'";
		MonitoringResourceDatasets msets = new MonitoringResourceDatasets();
		List<MonitoringResourceDataset> list = new ArrayList<MonitoringResourceDataset>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlStatement);
			while (rs.next()) {
				Timestamp sp = rs.getTimestamp("metric_timestamp");
				Date date = new Date(sp.getTime());
				MonitoringResourceDataset ms = new MonitoringResourceDataset(rs
						.getString("service_resource_Id"), rs
						.getString("virtual_resource_id"), rs
						.getString("physical_resource_id"), resourceType, rs
						.getString("monitoring_information_collector_id"), rs
						.getString("metric_name"),
						rs.getString("metric_value"), rs
								.getString("metric_unit"), date);
				list.add(ms);
				date = null;
			}
			msets.setMonitoring_resource(list);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage() + ":"
					+ e.getSQLState());
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			;
		}
		return msets;
	}

	public static MonitoringResourceDatasets getResource_type_mname(
			Connection conn, String resourceType, String metricName,
			java.util.Date dfrom, java.util.Date dto) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		String sfrom = sdf.format(dfrom);
		String sto = sdf.format(dto);
		String sqlStatement = null;
		if (resourceType.contains("service"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
					+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_service "
					+ "WHERE metric_name='"
					+ metricName
					+ "' AND metric_timestamp BETWEEN '"
					+ sfrom
					+ "' AND '"
					+ sto + "'";
		else if (resourceType.contains("virtual"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_virtual "
				+ "WHERE metric_name='"
				+ metricName
				+ "' AND metric_timestamp BETWEEN '"
				+ sfrom
				+ "' AND '"
				+ sto + "'";
		else if (resourceType.contains("physical"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_physical "
				+ "WHERE metric_name='"
				+ metricName
				+ "' AND metric_timestamp BETWEEN '"
				+ sfrom
				+ "' AND '"
				+ sto + "'";
		else if (resourceType.contains("energy"))
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_energy "
				+ "WHERE metric_name='"
				+ metricName
				+ "' AND metric_timestamp BETWEEN '"
				+ sfrom
				+ "' AND '"
				+ sto + "'";
		else return new MonitoringResourceDatasets();
		MonitoringResourceDatasets msets = new MonitoringResourceDatasets();
		List<MonitoringResourceDataset> list = new ArrayList<MonitoringResourceDataset>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlStatement);
			while (rs.next()) {
				Timestamp sp = rs.getTimestamp("metric_timestamp");
				Date date = new Date(sp.getTime());
				MonitoringResourceDataset ms = new MonitoringResourceDataset(rs
						.getString("service_resource_Id"), rs
						.getString("virtual_resource_id"), rs
						.getString("physical_resource_id"), resourceType, rs
						.getString("monitoring_information_collector_id"), rs
						.getString("metric_name"),
						rs.getString("metric_value"), rs
								.getString("metric_unit"), date);
				list.add(ms);
				date = null;
			}
			msets.setMonitoring_resource(list);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage() + ":"
					+ e.getSQLState());
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
			;
		}
		return msets;
	}

}
