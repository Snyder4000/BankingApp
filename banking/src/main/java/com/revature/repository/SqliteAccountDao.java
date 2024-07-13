package com.revature.repository;

import com.revature.entity.Account;
import com.revature.exception.AccountSQLException;
import com.revature.utility.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteAccountDao implements AccountDao{

    @Override
    public Account createCheckingAccount(Account newAccount) {
        // TODO Auto-generated method stub
        String sql = "insert into checking(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Checking account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }

    @Override
    public Account createSavingAccount(Account newAccount) {
        // TODO Auto-generated method stub
        String sql = "insert into saving(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Savings account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }

    @Override
    public Account createInvestmentAccount(Account newAccount) {
        // TODO Auto-generated method stub
        String sql = "insert into investment(user_id, gold, silver, copper) values (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newAccount.getUserId());
            preparedStatement.setFloat(2, newAccount.getGold());
            preparedStatement.setFloat(3, newAccount.getSilver());
            preparedStatement.setFloat(4, newAccount.getCopper());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return newAccount;
            }
            throw new AccountSQLException("Investment account could not be created: please try again");
        } catch (SQLException exception){
            throw new AccountSQLException(exception.getMessage());
        }
    }
    
    @Override
    public List<Account> getAllAccounts() {
        // TODO Auto-generated method stub
        String sql = "select * from checking";
        try(Connection connection = DatabaseConnector.createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            // TODO: handle exception
            throw new AccountSQLException(e.getMessage());
        }
        
    }

    @Override
    public Account getAccountByID(int id) {
        String sql = "select * from checking where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Account accountRecord = new Account();
            accountRecord.setUserId(resultSet.getInt("user_id"));
            accountRecord.setGold(resultSet.getFloat("gold"));
            accountRecord.setSilver(resultSet.getFloat("silver"));
            accountRecord.setCopper(resultSet.getFloat("copper"));
            accountRecord.setAccountID(resultSet.getInt("account_id"));
            return accountRecord;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public void deposit(Account acc, float g, float s, float c) {
        String sql = "update checking set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() + g);
            preparedStatement.setFloat(2, acc.getSilver() + s);
            preparedStatement.setFloat(3, acc.getCopper() + c);
            preparedStatement.setInt(4, acc.getAccountID());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public void withdraw(Account acc, float g, float s, float c) {
        String sql = "update checking set gold = ?, silver = ?, copper = ? where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, acc.getGold() - g);
            preparedStatement.setFloat(2, acc.getSilver() - s);
            preparedStatement.setFloat(3, acc.getCopper() - c);
            preparedStatement.setInt(4, acc.getAccountID());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAllAccountsByUserID(int id) {
        String sql = "select * from checking where user_id = ?;";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(resultSet.next()){
                Account accountRecord = new Account();
                accountRecord.setUserId(resultSet.getInt("user_id"));
                accountRecord.setGold(resultSet.getFloat("gold"));
                accountRecord.setSilver(resultSet.getFloat("silver"));
                accountRecord.setCopper(resultSet.getFloat("copper"));
                accountRecord.setAccountID(resultSet.getInt("account_id"));
                accounts.add(accountRecord);
            }
            return accounts;
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }

    @Override
    public void deleteAccount(Account acc) {
        String sql = "delete from checking where account_id = ?";
        try(Connection connection = DatabaseConnector.createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acc.getAccountID());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new AccountSQLException(e.getMessage());
        }
    }
 
}
