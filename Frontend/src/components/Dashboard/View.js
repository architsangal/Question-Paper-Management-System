import React from 'react';
import 'semantic-ui-css/semantic.min.css'

import {
  Container,
  Segment,
  Item,
  Divider,
  Button,
  Message,
  Menu,
  Icon
} from 'semantic-ui-react';


const View = ({data, setIsViewing}) => {
  return (
  <div className="small-container" style={{marginTop:"5vw"}}>
    {/* green tick mark
    <div className="ui icon message">
    */}
    <Item.Header>
      <Container>
        <Segment>
          <Item.Group divided>
            <Item>
              <Item.Content>
                <Item.Meta>
                  <Message size="huge" floating>
                    {data['mcq'] != null ? <b>{`Q. ${data['mcq'][0]['quesText']}`}</b> : <b>{`Q. ${data['subq']['quesText']}`}</b>}
                  </Message>
                  <Divider />
                  {data['mcq'] != null ? (
                  <Menu vertical fluid size="massive">
                    {data['mcq'].map((option, i) => {
                      return (
                        <Menu.Item
                          key= {option["mcqKey"]["optionNum"]}
                          name={option["optionText"]}
                          active={option["isCorrect"]}
                          color={option["isCorrect"] ? 'green' : 'red'}
                        >
                          <b style={{ marginRight: '8px' }}>{option["mcqKey"]["optionNum"]+1}.</b>
                          {option["optionText"]} {option["isCorrect"] ? <Icon name='checkmark' /> : null}
                        </Menu.Item>
                      );
                    })}
                  </Menu>) : (
                    <Message size="huge" floating>
                    <b>{`Ans. ${data['subq']['ans']}`}</b>
                  </Message>)
                  }
                </Item.Meta>
                <Divider />
                <Item.Extra>
                  <Button
                    primary
                    content="Go Back"
                    onClick={() => setIsViewing(false)}

                    floated="left"
                    size="big"
                    icon="left chevron"
                    labelPosition="left"
                  />
                </Item.Extra>
              </Item.Content>
            </Item>
          </Item.Group>
        </Segment>
      </Container>
    </Item.Header>
  </div>
  );
};


export default View;
