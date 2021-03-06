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

package eu.optimis.mi.aggregator.queries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import eu.optimis.mi.monitoring_resources.MonitoringResourceDataset;
import eu.optimis.mi.monitoring_resources.MonitoringResourceDatasets;

public class ServiceLevelQuery {
	public static MonitoringResourceDatasets getResource_sid_type(Connection conn,String serviceId, String resourceType) {
		String sqlStatement = null;
		if (resourceType.contains("physical")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_physical " +
						"WHERE service_resource_id ='"+ serviceId;
		}
		else if (resourceType.contains("virtual")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_virtual " +
						"WHERE service_resource_id ='"+ serviceId;
		}
		else if (resourceType.contains("service")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_service " +
						"WHERE service_resource_id ='"+ serviceId;
		}
		else if (resourceType.contains("energy")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_energy " +
						"WHERE service_resource_id ='"+ serviceId;
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
	
	public static MonitoringResourceDatasets getResource_sid_type_metricName(Connection conn,String serviceId, String resourceType, String metricName) {
		String sqlStatement = null;
		if (resourceType.contains("physical")){
		sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id, monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_physical " +
						"WHERE service_resource_id='"+serviceId+"' and metric_name='"+metricName+"'";
		}
		else if (resourceType.contains("virtual")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_virtual " +
				"WHERE service_resource_id='"+serviceId+"' and metric_name='"+metricName+"'";
		}
		else if (resourceType.contains("service")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_service " +
				"WHERE service_resource_id='"+serviceId+"' and metric_name='"+metricName+"'";
		}
		else if (resourceType.contains("energy")){
			sqlStatement = "SELECT service_resource_id,virtual_resource_id, physical_resource_id,monitoring_information_collector_id,"
				+ "metric_name,metric_value, metric_unit,metric_timestamp FROM monitoring_resource_energy " +
				"WHERE service_resource_id='"+serviceId+"' and metric_name='"+metricName+"'";
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
}
