package com.life.jfx.account;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.life.jfx.dao.life.AccountDao;
import com.life.jfx.dao.life.impl.AccountDaoImpl;
import com.life.jfx.pojo.account.Account;
import com.life.jfx.pojo.account.QueryAccountList;
import com.life.jfx.table.EditingCell;

/**
 * A simple table that uses cell factories to add a control to a table column
 * and to enable editing of first/last name and email.
 * 
 * @see javafx.scene.control.TableCell
 * @see javafx.scene.control.TableColumn
 * @see javafx.scene.control.TablePosition
 * @see javafx.scene.control.TableRow
 * @see javafx.scene.control.TableView
 */
public class AccountManager extends Application {

	private TableView tableView;

	private void init(Stage primaryStage) {

		// AccountDao accountDao = new AccountDaoImpl();
		// QueryAccountList queryAccountList = new QueryAccountList();
		// queryAccountList.setPageno(0);
		// queryAccountList.setPagesize(10);
		// List<Account> list = accountDao.getAccountList(queryAccountList);
		// final ObservableList<?> dataList = FXCollections
		// .observableArrayList(list);

		TableColumn nameCol;
		TableColumn aidCol;
		TableColumn apwdCol;
		TableColumn urlCol;
		// Set cell factory for cells that allow editing
		Callback<TableColumn<Object, Object>, TableCell<Object, Object>> editCellFactory = new Callback<TableColumn<Object, Object>, TableCell<Object, Object>>() {
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};
		tableView = TableViewBuilder
				.create()
				.columns(
						nameCol = TableColumnBuilder
								.create()
								.text("名称")
								.prefWidth(100)
								.cellValueFactory(
										new Callback<TableColumn.CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
											@Override
											public ObservableValue<Object> call(
													CellDataFeatures<Object, Object> param) {
												Account account = (Account) param
														.getValue();
												return new ReadOnlyObjectWrapper(
														account.getName());
											}

										})
								.cellFactory(
										new Callback<TableColumn<Object, Object>, TableCell<Object, Object>>() {
											@Override
											public TableCell<Object, Object> call(
													TableColumn<Object, Object> param) {
												return new EditingCell();
											}
										}).build(),
						aidCol = TableColumnBuilder
								.create()
								.text("账号")
								.prefWidth(100)
								.editable(true)
								.cellValueFactory(
										new Callback<TableColumn.CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
											@Override
											public ObservableValue<Object> call(
													CellDataFeatures<Object, Object> param) {
												Account account = (Account) param
														.getValue();
												return new ReadOnlyObjectWrapper(
														account.getAid());
											}

										}).cellFactory(editCellFactory).build(),
						apwdCol = TableColumnBuilder
								.create()
								.text("密码")
								.prefWidth(100)
								.editable(true)
								.cellValueFactory(
										new Callback<TableColumn.CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
											@Override
											public ObservableValue<Object> call(
													CellDataFeatures<Object, Object> param) {
												Account account = (Account) param
														.getValue();
												return new ReadOnlyObjectWrapper(
														account.getApwd());
											}

										}).cellFactory(editCellFactory).build(),
						urlCol = TableColumnBuilder
								.create()
								.text("网址")
								.minWidth(150)
								.editable(true)
								.cellValueFactory(
										new Callback<TableColumn.CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
											@Override
											public ObservableValue<Object> call(
													CellDataFeatures<Object, Object> param) {
												Account account = (Account) param
														.getValue();
												return new ReadOnlyObjectWrapper(
														account.getUrl());
											}

										}).cellFactory(editCellFactory).build()

				)
				// .items((ObservableList<Object>) dataList)
				.editable(true).build();

		Pagination pagination = PaginationBuilder.create().pageCount(7)
				.pageFactory(new Callback<Integer, Node>() {
					@Override
					public Node call(Integer pageIndex) {
						getCurrPage(pageIndex);
						return tableView;
					}

				}).build();

		ChoiceBox choiceBox;
		List<Object> list = new ArrayList<Object>();
		list.add("名称");
		list.add("账号");
		final ObservableList<Object> searchList = FXCollections
				.observableList(list);
		Scene scene = SceneBuilder
				.create()
				.root(VBoxBuilder
						.create()
						.alignment(Pos.TOP_CENTER)
						.padding(new Insets(5, 10, 5, 10))
						.children(
								LabelBuilder
										.create()
										.text("账号信息管理")
										.style("-fx-padding: 10;-fx-text-fill:black")
										.build(),
								HBoxBuilder
										.create()
										.alignment(Pos.CENTER_LEFT)
										.spacing(5)
										.padding(new Insets(5, 0, 5, 0))
										.children(
												choiceBox = ChoiceBoxBuilder
														.create()
														.items(searchList)
														.build(),
												TextFieldBuilder.create()
												// .promptText("名称")
														.prefWidth(100).build(),
												ButtonBuilder.create().text("搜索").alignment(Pos.CENTER_RIGHT).build()
												//.translateX(100)
//												ButtonBuilder.create().text("新增").alignment(Pos.CENTER_RIGHT).build()		
												)
										.build(), pagination).build()

				).build();
		updateObservableListProperties(nameCol, aidCol, apwdCol, urlCol);
		choiceBox.getSelectionModel().selectFirst();

		primaryStage.setScene(scene);

	}

	private void getCurrPage(Integer pageIndex) {
		AccountDao accountDao = new AccountDaoImpl();
		QueryAccountList queryAccountList = new QueryAccountList();
		queryAccountList.setPageno(pageIndex);
		queryAccountList.setPagesize(10);
		List<Account> list = accountDao.getAccountList(queryAccountList);
		final ObservableList<?> dataList = FXCollections
				.observableArrayList(list);
		tableView.setItems(dataList);
	}

	private void updateObservableListProperties(TableColumn nameCol,
			TableColumn aidCol, TableColumn apwdCol, TableColumn urlCol) {
		// Modifying the email property in the ObservableList
		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Account, String>>() {
			@Override
			public void handle(CellEditEvent<Account, String> t) {
				((Account) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setName(t
						.getNewValue());
			}
		});
		// Modifying the email property in the ObservableList
		aidCol.setOnEditCommit(new EventHandler<CellEditEvent<Account, String>>() {
			@Override
			public void handle(CellEditEvent<Account, String> t) {
				((Account) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setAid(t
						.getNewValue());
			}
		});
		// Modifying the firstName property in the ObservableList
		apwdCol.setOnEditCommit(new EventHandler<CellEditEvent<Account, String>>() {
			@Override
			public void handle(CellEditEvent<Account, String> t) {
				((Account) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setApwd(t
						.getNewValue());
			}
		});
		// Modifying the lastName property in the ObservableList
		urlCol.setOnEditCommit(new EventHandler<CellEditEvent<Account, String>>() {
			@Override
			public void handle(CellEditEvent<Account, String> t) {
				((Account) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setUrl(t
						.getNewValue());
			}
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
