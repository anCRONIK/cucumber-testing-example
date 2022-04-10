import { NavBarChildren } from 'components/Navbar/NavbarChildren';
import { NavBarDesktop } from 'components/Navbar/NavbarDesktop';
import { NavBarMobile } from 'components/Navbar/NavbarMobile';
import React from 'react';
import styled from 'styled-components';
import { Media } from 'styles/AppMedia';
import backgroundImg from 'assets/background.png';

export class NavBar extends React.Component {
  state = {
    visible: false,
  };

  handlePusher = () => {
    const { visible } = this.state;

    if (visible) this.setState({ visible: false });
  };

  handleToggle = () => this.setState({ visible: !this.state.visible });

  render() {
    const { children, leftItems, rightItems } = this.props;
    const { visible } = this.state;

    return (
      <NavbarDiv>
        <Media at="mobile">
          <NavBarMobile
            leftItems={leftItems}
            onPusherClick={this.handlePusher}
            onToggle={this.handleToggle}
            rightItems={rightItems}
            visible={visible}
          >
            <NavBarChildren>{children}</NavBarChildren>
          </NavBarMobile>
        </Media>

        <Media greaterThan="mobile">
          <NavBarDesktop leftItems={leftItems} rightItems={rightItems} />
          <NavBarChildren>{children}</NavBarChildren>
        </Media>
      </NavbarDiv>
    );
  }
}

const NavbarDiv = styled.div`
  background-image: url(${backgroundImg});
  background-repeat: no-repeat;
  background-size: 100% 100%;
`;
